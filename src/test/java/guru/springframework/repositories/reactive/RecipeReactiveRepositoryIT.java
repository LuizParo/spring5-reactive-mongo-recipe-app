package guru.springframework.repositories.reactive;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import guru.springframework.domain.Recipe;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryIT {
    private static final String ID = "12354";
    public static final String DESCRIPTION = "description";

    @Autowired
    private RecipeReactiveRepository recipeReactiveRepository;

    @Before
    public void setUp() {
        this.recipeReactiveRepository.deleteAll().block();
    }

    @Test
    public void testeSave() {
        Recipe recipe = new Recipe();
        recipe.setId(ID);
        recipe.setDescription(DESCRIPTION);

        this.recipeReactiveRepository.save(recipe).block();

        long count = this.recipeReactiveRepository.count().block();
        assertEquals(1L, count);
    }
}