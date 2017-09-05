package guru.springframework.repositories.reactive;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.domain.Category;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryIT {
    private static final String ID = "8776";
    private static final String DESCRIPTION = "description";

    @Autowired
    private CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() {
        this.categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSave() {
        Category category = new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION);

        this.categoryReactiveRepository.save(category).block();

        long count = this.categoryReactiveRepository.count().block();
        assertEquals(1L, count);
    }

    @Test
    public void testFindByDescription() {
        Category category = new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION);

        this.categoryReactiveRepository.save(category).then().block();

        Category fetchedCategory = this.categoryReactiveRepository.findByDescription(DESCRIPTION).block();
        assertNotNull(fetchedCategory);
        assertEquals(DESCRIPTION, fetchedCategory.getDescription());
    }
}