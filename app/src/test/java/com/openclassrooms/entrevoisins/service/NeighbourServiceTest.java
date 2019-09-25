package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
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

    @Test
    public void getNeighbourDetailWithSuccess() {
        Neighbour neighbourGetDetail = service.getNeighbours().get(0);
        service.neighbourGetDetail(neighbourGetDetail);
        assertTrue(service.getNeighbours().contains(neighbourGetDetail));
    }

    /**
     * assert that the list is not null
     */
    @Test
    public void getFavoriteWithSuccess(){
        assertNotNull(service.getFavorites());
    }

    /**
     * Assert that the add method is correct
     */
    @Test
    public void addFavoriteWithSuccess(){
        Neighbour neighbour = new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "Saint Pierre du mont à 5km","+33 6 86 57 90 14", "caroline", "Bonjour! Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner! J'aime les jeux de cartes tels la belote et le tarot.");
        service.addNeighbourToFavorite(neighbour);
        assertEquals(1, service.getFavorites().size());
    }

    /**
     * assert that the neighbour is well remove from the list
     */
    @Test
    public void deleteFavoriteWithSuccess(){
        Neighbour neighbour = new Neighbour(1, "Caroline", "http://i.pravatar.cc/150?u=a042581f4e29026704d", "Saint Pierre du mont à 5km","+33 6 86 57 90 14", "caroline", "Bonjour! Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner! J'aime les jeux de cartes tels la belote et le tarot.");
        Neighbour neighbour2 = new Neighbour(2, "Jack", "http://i.pravatar.cc/150?u=a042581f4e29026704e", "Mont-de-Marsan","+33 7 54 97 82 14", "Jack", "Salut ! Fan de Harley Davidson et rocker dans l'âme je cherche des compagnons de route pour faire des virées folles !");
        service.addNeighbourToFavorite(neighbour);
        service.addNeighbourToFavorite(neighbour2);
        service.deleteNeighbourFromFavorite(neighbour);
        assertFalse(service.getFavorites().contains(neighbour));
        assertTrue(service.getFavorites().contains(neighbour2));
    }
}
