package com.liveaction.google.hashcode2019.model;

import com.google.common.collect.Sets;

public class Solver {

    public void solve() {

    }

    int score(Slide s1, Slide s2) {
        Sets.SetView<String> intersection = Sets.intersection(s1.tags(), s2.tags());
        Sets.SetView<String> difference1 = Sets.difference(s1.tags(), s2.tags());
        Sets.SetView<String> difference2 = Sets.difference(s2.tags(), s1.tags());
        return Math.min(intersection.size(), Math.min(difference1.size(), difference2.size()));
    }

}
