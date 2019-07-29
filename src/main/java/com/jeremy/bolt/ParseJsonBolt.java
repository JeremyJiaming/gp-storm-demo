package com.jeremy.bolt;

import com.alibaba.fastjson.JSON;
import com.jeremy.domain.Person;
import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

import java.util.Map;

/**
 * 将 Person 转为 json字符串的Bolt
 */
public class ParseJsonBolt extends BaseRichBolt {

    OutputCollector collector;

    @Override
    public void prepare(Map map, TopologyContext topologyContext, OutputCollector outputCollector) {
        this.collector = outputCollector;
    }

    @Override
    public void execute(Tuple tuple) {
        Person person = (Person) tuple.getValueByField("person");
        String jsonString = JSON.toJSONString(person);

        System.out.println(jsonString);
        collector.emit(new Values(jsonString));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("jsonPerson"));
    }
}
