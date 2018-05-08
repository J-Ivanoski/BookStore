package mk.ukim.finki.emt.persistence;

import mk.ukim.finki.emt.model.jpa.Book;
import mk.ukim.finki.emt.model.jpa.DeliveryPackage;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Riste Stojanov
 */
public interface QueryRepository {

  Page<Book> findBooksByCategoryPaged(Long categoryId, int page, int pageSize);

  Page<Book> findPromotedBooks(int page, int pageSize);

  public List<DeliveryPackage> findDeliveryPackages();
}
