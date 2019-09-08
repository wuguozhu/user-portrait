package com.anan.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.Map;
import java.util.Set;


/**
 * @author wuguozhu
 * @date 2019/9/7
 */

public class HbaseUtils {

    private static Admin admin = null;
    private static Connection conn = null;

    //创建Hbase的配置对象
    static {
        //创建Hbase的配置对象
        Configuration conf = HBaseConfiguration.create();
        //设置连接参数
        conf.set("hbase.rootdir", "hdfs://192.168.80.134:9000/hbase");
        conf.set("hbase.zookeeper.quorum", "192.168.80.134");
        conf.set("hbase.client.scanner.timeout.period", "600000");
        conf.set("hbase.rpc.timeout", "600000");
        try {
            //获取管理程序
            conn = ConnectionFactory.createConnection(conf);
            admin = conn.getAdmin();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 转换为Byte方式按行插入数据
     *
     * @param tablename 表名:userflaginfo
     * @param rowkey rowkey
     * @param famliyname 列族
     * @param datamap map数据
     * @throws Exception
     */
    public static void put(String tablename,
                           String rowkey,
                           String famliyname,
                           Map<String, String> datamap) throws Exception {
        //获取Hbase表
        Table table = conn.getTable(TableName.valueOf(tablename));

        //将字符串转换为byte[]
        byte[] rowkeybyte = Bytes.toBytes(rowkey);

        //构造hbase put 对象
        Put put = new Put(rowkeybyte);
        if (datamap != null) {
            Set<Map.Entry<String, String>> set = datamap.entrySet();

            for (Map.Entry<String, String> entry : set) {
                String key = entry.getKey();
                Object value = entry.getValue();
                put.addColumn(Bytes.toBytes(famliyname),
                        Bytes.toBytes(key),
                        Bytes.toBytes(value + ""));
            }
        }

        //put数据
        table.put(put);

        //关闭连接
        table.close();
        System.out.println("put data to hbase finish");
    }

    /**
     *
     * @param tablename
     * @param rowkey
     * @param famliyname
     * @param colum
     * @param data
     * @throws Exception
     */
    public static void putdata(String tablename,
                               String rowkey,
                               String famliyname,
                               String colum,
                               String data) throws Exception {
        Table table = conn.getTable(TableName.valueOf(tablename));
        Put put = new Put(rowkey.getBytes());
        put.addColumn(famliyname.getBytes(), colum.getBytes(), data.getBytes());
        table.put(put);
    }

    /**
     *
     * @param tablename
     * @param rowkey
     * @param famliyname
     * @param colum
     * @return
     * @throws Exception
     */
    public static String getdata(String tablename,
                                 String rowkey,
                                 String famliyname,
                                 String colum) throws Exception {
        Table table = conn.getTable(TableName.valueOf(tablename));
        // 将字符串转换成byte[]
        byte[] rowkeybyte = Bytes.toBytes(rowkey);
        Get get = new Get(rowkeybyte);
        Result result = table.get(get);
        byte[] resultbytes = result.getValue(famliyname.getBytes(), colum.getBytes());
        if (resultbytes == null) {
            return null;
        }

        return new String(resultbytes);
    }

}
