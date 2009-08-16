import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class TransactionReducer extends MapReduceBase implements Reducer<IntWritable, FloatWritable, IntWritable, FloatWritable> {

  public void reduce(IntWritable key, Iterator<FloatWritable> values, OutputCollector<IntWritable, FloatWritable> output, Reporter reporter) throws IOException {

    float sum = 0;
    while (values.hasNext()) {
      FloatWritable value = (FloatWritable) values.next();
      sum += value.get();
    }

    output.collect(key, new FloatWritable(sum));
  }
}

