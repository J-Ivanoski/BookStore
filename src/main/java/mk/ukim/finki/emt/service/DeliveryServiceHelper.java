package mk.ukim.finki.emt.service;

import mk.ukim.finki.emt.model.enums.DeliveryStatus;
import mk.ukim.finki.emt.model.jpa.DeliveryPackage;
import mk.ukim.finki.emt.model.jpa.DeliveryInfo;

/**
 * Created by jovica on 3/15/17.
 */
public interface DeliveryServiceHelper {

    DeliveryPackage createDeliveryPackage(
            Long checkoutId
    );


    DeliveryPackage updateDeliveryPackageStatus(
            Long deliveryId,
            DeliveryStatus newStatus //,
    );

    DeliveryPackage readDeliveryPackage(
            Long deliveryId
    );


    /*boolean removeDeliveryPackage(
            Long deliveryId
    );*/

    /*create and update*/
    DeliveryPackage preparedDelivery(
            Long deliveryId
    );

    /*read and update*/
    DeliveryPackage shippedDelivery(
            Long deliveryId
    );

    /*updage and delete*/
    DeliveryPackage closeDeliveryWithoutConfirmation(
            Long deliveryId
    );
}
