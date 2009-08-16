import java.io.IOException;

import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class TransactionMapper extends MapReduceBase implements Mapper<Text, Text, IntWritable, FloatWritable> {

  private IntWritable account1 = new IntWritable();
  private IntWritable account2 = new IntWritable();
  private FloatWritable balance1 = new FloatWritable();
  private FloatWritable balance2 = new FloatWritable();

  public void map(Text key, Text value, OutputCollector<IntWritable, FloatWritable> output, Reporter reporter) throws IOException {
    String[] accounts = key.toString().split("->");

    account1.set(Integer.parseInt(accounts[0]));
    account2.set(Integer.parseInt(accounts[1]));

    float val = Float.parseFloat(value.toString());
    balance1.set( (float) 0.0 - val);
    balance2.set(val);

    output.collect(account1, balance1);
    output.collect(account2, balance2);
  }
}

