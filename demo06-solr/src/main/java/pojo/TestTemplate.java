package pojo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-solr.xml")
public class TestTemplate {
    @Autowired
    private SolrTemplate solrTemplate;

    @Test
    public void add(){
        TbItem item = new TbItem();
        // 主键相同，即修改
        item.setId(3L);
        item.setBrand("小米为");
        item.setCategory("手机pluse");
        item.setGoodsId(1L);
        item.setSeller("小米1号专卖店");
        item.setTitle("红米Mate9");
        item.setUpdateTime(new Date());
        item.setPrice(new BigDecimal(2000));

        solrTemplate.saveBean(item);
        solrTemplate.commit();

    }

    @Test
    public void find(){
        TbItem item = solrTemplate.getById(3L,TbItem.class);
        System.out.println(item.getTitle());
    }

    @Test
    public void delete(){
        solrTemplate.deleteById("3");
        solrTemplate.commit();
    }

    @Test
    public void addList(){
        List<TbItem> list=new ArrayList();
        for(int i=1;i<101;i++){
            TbItem item=new TbItem();
            item.setId(Long.valueOf(i));
            item.setBrand("华为");
            item.setCategory("手机");
            item.setGoodsId(1L);
            item.setSeller("华为"+i+"号专卖店");
            item.setTitle("华为Mate"+i);
            item.setPrice(new BigDecimal(2000+i));
            list.add(item);
        }

        solrTemplate.saveBeans(list);
        solrTemplate.commit();
    }

    //分页查询
    @Test
    public void testPageQuery(){
        Query query = new SimpleQuery("*:*");
        query.setOffset(10);
        query.setRows(20);
        ScoredPage<TbItem> tbItems = solrTemplate.queryForPage(query, TbItem.class);

        for (TbItem item : tbItems) {
            System.out.println(item.getTitle() + item.getPrice());
        }
    }

    //条件查询
    @Test
    public void testPageQueryMutil(){
        Query query = new SimpleQuery("*:*");

        //设置查询条件
        Criteria criteria = new Criteria("item_title").contains("2");
        criteria.and("item_price").greaterThan(2020);
        query.addCriteria(criteria);
        solrTemplate.queryForPage(query,TbItem.class);
    }

    @Test
    public void testDeleteAll(){
        Query query=new SimpleQuery("*:*");
        solrTemplate.delete(query);
        solrTemplate.commit();
    }

}
