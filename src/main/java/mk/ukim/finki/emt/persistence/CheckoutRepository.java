package mk.ukim.finki.emt.persistence;

import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.Category;
import mk.ukim.finki.emt.model.jpa.Checkout;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by jovica on 4/6/17.
 */
public interface CheckoutRepository extends CrudRepository<Checkout,Long>,
        JpaSpecificationExecutor<Checkout> {

    //List<Checkout> findAll();
    List<Checkout> findById(Long checkoutId);

}