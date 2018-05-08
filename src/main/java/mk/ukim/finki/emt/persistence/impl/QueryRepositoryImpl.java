package mk.ukim.finki.emt.persistence.impl;

import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.DeliveryPackage;
import mk.ukim.finki.emt.persistence.BookRepository;
import mk.ukim.finki.emt.persistence.DeliveryPackageRepository;
import mk.ukim.finki.emt.persistence.QueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Riste Stojanov
 */
@Repository
public class QueryRepositoryImpl implements QueryRepository {

  BookRepository bookRepository;
  DeliveryPackageRepository deliveryPackageRepository;

  @Autowired
  public QueryRepositoryImpl(BookRepository bookRepository, DeliveryPackageRepository deliveryPackageRepository) {
    this.bookRepository = bookRepository;
    this.deliveryPackageRepository = deliveryPackageRepository;
  }


  @Override
  public Page<Book> findBooksByCategoryPaged(Long categoryId, int page, int pageSize) {
    return bookRepository.findAll(
      (book, cq, cb) -> cb.equal(book.join("category").get("id"), categoryId),
      new PageRequest(page, pageSize)
    );
  }

  @Override
  public Page<Book> findPromotedBooks(int page, int pageSize) {
    return bookRepository.findAll(
      (book, cq, cb) -> cb.equal(book.get("promoted"), true),
      new PageRequest(page, pageSize)
    );
  }


  @Override
  public List<DeliveryPackage> findDeliveryPackages() {
    return deliveryPackageRepository.findAllPackages();

  }
}
