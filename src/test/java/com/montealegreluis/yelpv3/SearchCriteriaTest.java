/*
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.montealegreluis.yelpv3;

import com.montealegreluis.yelpv3.businesses.distance.Distance;
import com.montealegreluis.yelpv3.search.AreaTooLarge;
import com.montealegreluis.yelpv3.search.IncompatibleCriteria;
import com.montealegreluis.yelpv3.search.SearchCriteria;
import com.montealegreluis.yelpv3.search.TooManyResults;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.time.Instant;

import static com.montealegreluis.yelpv3.search.Attribute.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class SearchCriteriaTest {
    @Test
    public void it_does_not_allow_a_radius_bigger_than_40000_meters() {
        exception.expect(AreaTooLarge.class);

        SearchCriteria
            .byLocation("San Antonio")
            .withinARadiusOf(Distance.inMeters(40001))
        ;
    }

    @Test
    public void it_does_not_allow_more_than_50_results() {
        exception.expect(TooManyResults.class);

        SearchCriteria
            .byCoordinates(29.426786, -98.489576)
            .limit(51)
        ;
    }

    @Test
    public void it_does_not_allow_searches_with_open_at_and_open_now() {
        exception.expect(IncompatibleCriteria.class);

        SearchCriteria
            .byCoordinates(29.426786, -98.489576)
            .openNow()
            .openAt(Instant.now().getEpochSecond())
        ;
    }


    @Test
    public void it_adds_several_attributes() {
        SearchCriteria criteria = SearchCriteria
            .byLocation("San Antonio")
            .withAttributes(CASHBACK, DEALS, GENDER_NEUTRAL_RESTROOMS)
        ;

        assertThat(
            criteria.toString(),
            containsString("cashback,deals,gender_neutral_restrooms")
        );
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();
}