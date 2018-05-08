package mk.ukim.finki.emt.service;

import ch.qos.logback.core.CoreConstants;
import mk.ukim.finki.emt.model.enums.DeliveryStatus;
import mk.ukim.finki.emt.model.exceptions.CategoryInUseException;
import mk.ukim.finki.emt.model.jpa.Checkout;
import mk.ukim.finki.emt.model.jpa.DeliveryPackage;
import mk.ukim.finki.emt.service.impl.DeliveryHelperImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import mk.ukim.finki.emt.persistence.CheckoutRepository;
/**
 * Created by jovica on 3/15/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DeliveryHelperTest {

    @Autowired
    DeliveryHelperImpl serviceHelper;

    DeliveryPackage deliveryPackage;
    Checkout checkoutReff;

    CheckoutRepository chRepo;


    @After
    public void clearEntities() {}


    @Test
    public void testCrud() throws CategoryInUseException {

        //long b = (long) 1234;
        checkoutReff = new Checkout();
        checkoutReff=chRepo.save(checkoutReff);

        deliveryPackage = serviceHelper.createDeliveryPackage(checkoutReff.id);


        Assert.assertNotNull(deliveryPackage);
        Assert.assertNotNull(deliveryPackage.id);
        Assert.assertNotNull(serviceHelper.readDeliveryPackage(deliveryPackage.id));
        Assert.assertNotNull(deliveryPackage.checkout.id);


        Assert.assertEquals(DeliveryStatus.PENDING_PACKAGE_CREATION, deliveryPackage.status);
        //Assert.assertEquals(1l, (long)deliveryPackage.checkout.id);
        Assert.assertEquals(deliveryPackage.checkout.id, checkoutReff.id);


        serviceHelper.updateDeliveryPackageStatus(deliveryPackage.id,DeliveryStatus.PACKAGE_READY_FOR_SHIPMENT);

        Assert.assertEquals(DeliveryStatus.PACKAGE_READY_FOR_SHIPMENT, deliveryPackage.status);


        serviceHelper.preparedDelivery(deliveryPackage.id);

        Assert.assertEquals(DeliveryStatus.PACKAGE_READY_FOR_SHIPMENT, deliveryPackage.status);


        serviceHelper.shippedDelivery(deliveryPackage.id);

        Assert.assertEquals(DeliveryStatus.SHIPMENT_IN_PROGRESS, deliveryPackage.status);



        serviceHelper.closeDeliveryWithoutConfirmation(deliveryPackage.id);

        Assert.assertEquals(DeliveryStatus.CLOSED_UNCONFIRMED_DELIVERY, deliveryPackage.status);


        //serviceHelper.removeDeliveryPackage(deliveryPackage.id);

        //Assert.assertEquals(true, serviceHelper.removeDeliveryPackage(deliveryPackage.id));
        //Assert.assertNull(serviceHelper.readDeliveryPackage(deliveryPackage.id));




    }

}