package com.jeremy.app;

import com.jeremy.bolt.ParseJsonBolt;
import com.jeremy.bolt.printBolt;
import com.jeremy.spout.RandomPersonSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

public class PersonTopology {

    public static void main(String[] args) {

        //1.构建TopologyBuilder
        TopologyBuilder topologyBuilder = new TopologyBuilder();
        topologyBuilder.setSpout("spout",new RandomPersonSpout(),1);//并发度为1
        topologyBuilder.setBolt("parseJsonBolt",new ParseJsonBolt()).shuffleGrouping("spout");
        topologyBuilder.setBolt("printBolt",new printBolt()).shuffleGrouping("parseJsonBolt");

        //2.Config
        Config config = new Config();
        config.setDebug(false);
        config.setNumWorkers(1);

        //3.提交任务 --两种模式 本地模式和集群模式
        LocalCluster localCluster = new LocalCluster();
        localCluster.submitTopology("person_topology",config,topologyBuilder.createTopology());
    }

}
