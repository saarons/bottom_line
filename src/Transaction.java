import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.KeyValueTextInputFormat;

public class Transaction {

    public static void main(String[] args) {
        JobClient client = new JobClient();
        JobConf conf = new JobConf(Transaction.class);

        // specify input type
        conf.setInputFormat(KeyValueTextInputFormat.class);

        // specify output types
        conf.setOutputKeyClass(IntWritable.class);
        conf.setOutputValueClass(FloatWritable.class);

        // specify input and output dirs
        FileInputFormat.addInputPath(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        // specify a mapper
        conf.setMapperClass(TransactionMapper.class);

        // specify a reducer
        conf.setReducerClass(TransactionReducer.class);
        conf.setCombinerClass(TransactionReducer.class);

        client.setConf(conf);
        try {
            JobClient.runJob(conf);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

