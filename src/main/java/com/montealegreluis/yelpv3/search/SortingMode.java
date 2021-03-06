/*
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.montealegreluis.yelpv3.search;

public enum SortingMode {
    BEST_MATCH, // Default sorting mode
    RATING,
    REVIEW_COUNT,
    DISTANCE;

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
}
