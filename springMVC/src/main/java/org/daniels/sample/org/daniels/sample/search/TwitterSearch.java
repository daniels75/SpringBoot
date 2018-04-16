package org.daniels.sample.org.daniels.sample.search;

/**
 * Created by daniels on 16.04.2018.
 */
import java.util.List;

public interface TwitterSearch {
    List<LightTweet> search(String searchType, List<String> keywords);
}