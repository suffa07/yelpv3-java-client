/*
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.montealegreluis.yelpv3.businesses;

import java.util.Collections;
import java.util.List;

public class SearchResult {
    public final int total;
    public final List<BasicInformation> businesses;
    public final Region region;

    public SearchResult(int total, List<BasicInformation> businesses, Region region) {
        this.total = total;
        this.businesses = Collections.unmodifiableList(businesses);
        this.region = region;
    }
}
