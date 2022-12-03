import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductManagerTest {
    ProductRepository repo = new ProductRepository();
    ProductManager manager = new ProductManager(repo);

    Product book1 = new Book(12, 50, "Тараканище", "Чуковский А.И.");
    Product book2 = new Book(13, 130, "Война и мир", "Толстой Л.Н.");
    Product book3 = new Book(15, 80, "Обитаемый остров", "Братья Стругацких");
    Product book4 = new Book(17, 95, "Старик и море", "Э.Хемингуэй");
    Product smartphone1 = new Smartphone(56, 8990, "Xiaomi Redmi 9C", "Xiaomi");
    Product smartphone2 = new Smartphone(63, 8400, "Sony Xperia Z3", "Sony");
    Product smartphone3 = new Smartphone(71, 32200, "Sony Xperia 10", "Sony");

    @Test
    public void shouldFindAllProducts() {

        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(book4);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);

        Product[] expected = {book1, book2, book3, book4, smartphone1, smartphone2, smartphone3};
        Product[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchBy() {

        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(book4);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);

        Product[] expected = {smartphone1};
        Product[] actual = manager.searchBy("Xiaomi Redmi 9C");

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByWord() {

        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(book4);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);

        Product[] expected = {smartphone2, smartphone3};
        Product[] actual = manager.searchBy("Sony");

        assertArrayEquals(expected, actual);
    }

    /*
    Дополнительные тесты:
     */

    /*
    Тест на метод поиска "searchBy" при ситуации когда находится несколько товаров
     */

    @Test
    public void shouldSearchSomeProducts() {

        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);

        Product[] expected = {smartphone2, smartphone3};
        Product[] actual = manager.searchBy("Sony");

        assertArrayEquals(expected, actual);
    }

    /*
    Тест на метод поиска "searchBy" при ситуации когда находится ровно один товар
     */

    @Test
    public void shouldSearchOnlyOneProduct() {

        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);

        Product[] expected = {book1};
        Product[] actual = manager.searchBy("Тараканище");

        assertArrayEquals(expected, actual);
    }

    /*
    Тест на метод поиска "searchBy" при ситуации когда ни один товар не подходит
     */

    @Test
    public void shouldSearchNoProducts() {

        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);

        Product[] expected = {};
        Product[] actual = manager.searchBy("Тараканище");

        assertArrayEquals(expected, actual);
    }

    /*
    Тест удаления существующего элемента по id
     */

    @Test
    public void shouldRemoteByIdFromList() {

        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(book4);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);
        manager.remoteById(13);

        Product[] expected = {book1, book3, book4, smartphone1, smartphone2, smartphone3};
        Product[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

    /*
    Тест генерации NotFoundException при попытке удаления несуществующего элемента
     */

    @Test
    public void shouldRemoteByNonexistentIdFromList() {

        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(book4);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);

        assertThrows(NotFoundException.class,
                () -> manager.remoteById(14));
    }

     /*
    Тест на добавление товаров
     */

    @Test
    public void shouldAddProducts() {

        manager.add(book1);
        manager.add(book2);
        manager.add(book3);
        manager.add(book4);
        manager.add(smartphone1);
        manager.add(smartphone2);
        manager.add(smartphone3);

        Product[] expected = {book1, book2, book3, book4, smartphone1, smartphone2, smartphone3};
        Product[] actual = repo.findAll();

        assertArrayEquals(expected, actual);
    }

      /*
    Тест на метод поиска "searchBy" при ситуации когда находится несколько одинаковых товаров
     */

    @Test
    public void shouldSearchSameProducts() {

        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone3);

        assertThrows(AlreadyExistsException.class,
                () -> manager.add(smartphone3));
    }
}