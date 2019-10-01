package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    /**
     * assert that the clicked neighbour get to its detailed page
     */
    @Test
    public void getNeighbourDetailWithSuccess() {
        Neighbour neighbourGetDetail = service.getNeighbours().get(0);
        service.neighbourGetDetail(neighbourGetDetail);
        assertTrue(service.getNeighbours().contains(neighbourGetDetail));
    }

    /**
     * Assert that the neighbour is well added to favorites when fab clicked
     */
    @Test
    public void addFavoriteWithSuccess(){
        Neighbour neighbourAddedToFavorites = service.getNeighbours().get(0);
        service.addNeighbourToFavorite(neighbourAddedToFavorites);
        assertFalse(service.getFavorites().isEmpty());
    }

    /**
     * assert that when the neighbour is already in favorites, he's well removed from favorites when fab clicked or when is deleted directly from favorites list
     */
    @Test
    public void deleteFavoriteWithSuccess(){
        Neighbour neighbourDeleteFromFavorites = service.getNeighbours().get(0);
        service.deleteNeighbourFromFavorite(neighbourDeleteFromFavorites);
        assertFalse(service.getFavorites().contains(neighbourDeleteFromFavorites));
    }

    /**
     * assert that the list is not null
     */
    @Test
    public void getFavoriteWithSuccess(){
        assertNotNull(service.getFavorites());
    }
}
