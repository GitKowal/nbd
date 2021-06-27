import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.DeleteValue;
import com.basho.riak.client.api.commands.kv.FetchValue;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.core.RiakCluster;
import com.basho.riak.client.core.RiakNode;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;
import com.basho.riak.client.core.query.RiakObject;
import com.basho.riak.client.core.util.BinaryValue;

import java.net.UnknownHostException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class Main {
    private final static String KEY = "PrzyklKlucz";
    private final static String VALUE = "PrzyklWartość";
    private final static String MOD_VALUE = "PrzyklWartośćZmod";
    private final static String BUCKET = "s23871";

    private static RiakCluster setUpCluster() throws UnknownHostException {
        // This example will use only one node listening on localhost:10017
        RiakNode node = new RiakNode.Builder()
                .withRemoteAddress("127.0.0.1")
                .withRemotePort(8087)
                .build();

        // This cluster object takes our one node as an argument
        RiakCluster cluster = new RiakCluster.Builder(node)
                .build();

        // The cluster must be started to work, otherwise you will see errors
        cluster.start();

        return cluster;
    }

    public static void main(String[] args) throws UnknownHostException, ExecutionException, InterruptedException {
        System.out.println(String.format("Do bazy (bucket: %s) wrzucam dokument (klucz: %s; wartość: %s)", BUCKET, KEY, VALUE));
        RiakObject quoteObject = new RiakObject()
                .setContentType("text/plain")
                .setValue(BinaryValue.create(VALUE));
        Namespace bucket = new Namespace(BUCKET);
        Location location = new Location(bucket, KEY);
        StoreValue storeOp = new StoreValue.Builder(quoteObject)
                .withLocation(location)
                .build();
        RiakCluster cluster = setUpCluster();
        RiakClient client = new RiakClient(cluster);
        StoreValue.Response storeOpResp = client.execute(storeOp);

        FetchValue fetchOp = new FetchValue.Builder(location)
                .build();
        RiakObject fetchedObject = client.execute(fetchOp).getValue(RiakObject.class);
        System.out.println("Pobrana wartość spod klucza (" + KEY + "): "+ fetchedObject.getValue());

        fetchedObject.setValue(BinaryValue.create(MOD_VALUE));
        StoreValue updateOp = new StoreValue.Builder(fetchedObject)
                .withLocation(location)
                .build();
        StoreValue.Response updateOpResp = client.execute(updateOp);

        fetchedObject = client.execute(fetchOp).getValue(RiakObject.class);
        System.out.println("Pobrana wartość spod klucza (" + KEY + ") po modyfikacji: "+ fetchedObject.getValue());

        DeleteValue deleteOp = new DeleteValue.Builder(location)
                .build();
        client.execute(deleteOp);

        fetchedObject = client.execute(fetchOp).getValue(RiakObject.class);
        String value = Objects.isNull(fetchedObject) ? "null" : fetchedObject.getValue().toString();
        System.out.println("Pobrana wartość spod klucza (" + KEY + ") po usunięciu: " + value);

        cluster.shutdown();
    }
}
