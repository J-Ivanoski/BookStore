/*package mk.ukim.finki.emt.persistence.impl;

import mk.ukim.finki.emt.model.jpa.Checkout;
import mk.ukim.finki.emt.persistence.CheckoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by jovica on 4/6/17.
 *//*
@Repository
public class CheckoutRepositoryImpl implements CheckoutRepository {

    CheckoutRepository checkoutRepository;

    @Autowired
    public CheckoutRepositoryImpl(CheckoutRepository checkoutRepository) {
        this.checkoutRepository = checkoutRepository;
    }


    @Override
    public List<Checkout> findById(Long checkoutId) {
        /*return checkoutRepository.findAll(
                (book, cq, cb) -> cb.equal(book.join("category").get("id"), categoryId),
                new PageRequest(page, pageSize)
        );*//*
        return null;
    }
    @Override
    public List<Checkout> findAll() {
        return null;
    }
}
*/