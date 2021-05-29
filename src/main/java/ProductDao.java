import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductDao {
    static SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Product.class)
            .buildSessionFactory();

    public static void addToDb(String title, int cost) {
        Product product = new Product();
        Session session = factory.getCurrentSession();
        product.setTitle(title);
        product.setPrice(cost);
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
    }

    public static void findById(long id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Product product = session.find(Product.class, id);
        System.out.println(product);
    }

    public static void findAllProducts() {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<Product> productList = session.createQuery("select p from Product p", Product.class).getResultList();
        System.out.println(Arrays.toString(productList.toArray()));
    }

    public static void deleteById(long id) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.delete(product);
        session.getTransaction().commit();
    }

    public static void saveOrUpdate(long id, String title, int price) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Product product2 = session.find(Product.class, id);
        product2.setTitle(title);
        product2.setPrice(price);
        session.save(product2);
        session.getTransaction().commit();

    }

}
