package com.jeremy.spout;

import com.jeremy.domain.Person;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Utils;

import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * 随机产生Person对象，发送给bolt
 */
public class RandomPersonSpout extends BaseRichSpout {
    private SpoutOutputCollector collector;
    private Person[] persons = null;
    private Random random;

    //初始化
    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector collector) {
        this.collector = collector;
        random = new Random();
        //persons数组用来模拟流式数据
        persons = new Person[]{
                new Person("Jeremy",24,"Java开发",new Date()),
                new Person("BaoLiangLiang",21,"Java架构", new Date()),
                new Person("WuWenFeng",18,"大数据开发",new Date()),
        };
    }

    //上帝之手，循环调用【while(true)】,每调用过一次就发送一条消息
    @Override
    public void nextTuple() {
        Utils.sleep(1000);
        Person person = persons[random.nextInt(persons.length)];
        collector.emit(new Values(person));
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("person"));
    }
}
