package mk.ukim.finki.emt.service.impl;

import ch.qos.logback.classic.Logger;
import mk.ukim.finki.emt.model.enums.DeliveryStatus;
import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.Checkout;
import mk.ukim.finki.emt.model.jpa.DeliveryPackage;
import mk.ukim.finki.emt.persistence.CategoryRepository;
import mk.ukim.finki.emt.persistence.CheckoutRepository;
import mk.ukim.finki.emt.persistence.DeliveryPackageRepository;
import mk.ukim.finki.emt.service.CategoryServiceHelper;
import mk.ukim.finki.emt.service.DeliveryServiceHelper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

/**
 * Created by jovica on 3/15/17.
 */

@Service
public class DeliveryHelperImpl implements DeliveryServiceHelper {

   // static Logger logger = (Logger) LoggerFactory.getLogger(DeliveryServiceHelper.class);

    private CheckoutRepository checkoutRepository;

    private DeliveryPackageRepository deliveryPackageRepository;

    @Autowired
    public DeliveryHelperImpl(
            CheckoutRepository checkoutRepository,
            DeliveryPackageRepository deliveryPackageRepository
    ) {
        this.checkoutRepository = checkoutRepository;
        this.deliveryPackageRepository = deliveryPackageRepository;
    }

    @Override
    public DeliveryPackage createDeliveryPackage(Long checkoutId) {
        DeliveryPackage dp = new DeliveryPackage();
        dp.status=DeliveryStatus.PACKAGE_READY_FOR_SHIPMENT;
       // dp.checkout= (Checkout) checkoutRepository.findById((Long) checkoutId);
        dp.checkout = (Checkout) checkoutRepository.findOne((Long) checkoutId);
        return deliveryPackageRepository.save(dp);
        //return dp;
        //da se prasha...dali se kreira u baza...i za shto tochno sluzhi checkoutRepository...dali treba da se pravi posbno
    }

    @Override
    public DeliveryPackage readDeliveryPackage(Long checkoutId){
        //DeliveryPackage dp = (DeliveryPackage) deliveryPackageRepository.findById(checkoutId);
        DeliveryPackage dp = (DeliveryPackage) deliveryPackageRepository.findOne(checkoutId);
        //return deliveryPackageRepository.save(dp);
        return dp;
    }

    /*public Page<DeliveryPackage> readAllDeliveryPackages(){
        //DeliveryPackage dp = (DeliveryPackage) deliveryPackageRepository.findById(checkoutId);
        //DeliveryPackage dp = (DeliveryPackage) deliveryPackageRepository.findAll();
        //return deliveryPackageRepository.save(dp);
        return deliveryPackageRepository.findAll();
    }*/
    @Override
    public DeliveryPackage updateDeliveryPackageStatus(Long checkoutId, DeliveryStatus newStatus) {
        DeliveryPackage dp = readDeliveryPackage(checkoutId);
        dp.status = newStatus;
        return deliveryPackageRepository.save(dp);
        //return dp;
    }

    /*create and update*/
    @Override
    public DeliveryPackage preparedDelivery(Long deliveryId) {
        DeliveryPackage dp = createDeliveryPackage(deliveryId);
        dp = updateDeliveryPackageStatus(deliveryId, DeliveryStatus.PACKAGE_READY_FOR_SHIPMENT);
        return deliveryPackageRepository.save(dp);
        //return dp;
    }

    /*read and update*/
    @Override
    public DeliveryPackage shippedDelivery(Long deliveryId) {
        DeliveryPackage dp = readDeliveryPackage(deliveryId);
        dp = updateDeliveryPackageStatus(deliveryId, DeliveryStatus.SHIPMENT_IN_PROGRESS);
        //return deliveryPackageRepository.save(dp);
        return dp;
    }

    /*read and update*/
    @Override
    public DeliveryPackage closeDeliveryWithoutConfirmation(Long deliveryId) {
        DeliveryPackage dp = readDeliveryPackage(deliveryId);
        dp = updateDeliveryPackageStatus(deliveryId, DeliveryStatus.CLOSED_UNCONFIRMED_DELIVERY);
        //return deliveryPackageRepository.save(dp);
        return dp;
    }


}
