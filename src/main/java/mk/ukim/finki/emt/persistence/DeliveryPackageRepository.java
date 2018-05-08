package mk.ukim.finki.emt.persistence;

import mk.ukim.finki.emt.model.jpa.Category;
import mk.ukim.finki.emt.model.jpa.Checkout;
import mk.ukim.finki.emt.model.jpa.DeliveryPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by jovica on 4/6/17.
 */
public interface DeliveryPackageRepository extends CrudRepository<DeliveryPackage,Long>,
        JpaSpecificationExecutor<DeliveryPackage>, JpaRepository<DeliveryPackage, Long> {

    //List<DeliveryPackage> findAll();
    //List<DeliveryPackage> findById(Long checkoutId);
    @Query("SELECT dp FROM mk.ukim.finki.emt.model.jpa.DeliveryPackage dp")
    public List<DeliveryPackage> findAllPackages();

}