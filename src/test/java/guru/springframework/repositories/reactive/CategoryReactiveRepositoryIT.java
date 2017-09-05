package guru.springframework.repositories.reactive;

import static org.junit.Assert.*;

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
        Category category = new Category();
        category.setId(ID);
        category.setDescription(DESCRIPTION);

        this.categoryReactiveRepository.save(category);
    }

    @Test
    public void fetchCategories() {
        Category category = this.categoryReactiveRepository.findById(ID).block();
        assertEquals(ID, category.getId());
        assertEquals(DESCRIPTION, category.getDescription());
    }
}