package org.daniels.sample.search;

/**
 * Created by daniels on 16.04.2018.
 */
import org.daniels.sample.org.daniels.sample.search.LightTweet;
import org.daniels.sample.org.daniels.sample.search.TwitterSearch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Arrays;

@Configuration
public class StubTwitterSearchConfig {

    @Primary
    @Bean
    public TwitterSearch twitterSearch() {
        return (searchType, keywords) -> Arrays.asList(
                new LightTweet("tweetText"),
                new LightTweet("secondTweet")
        );
    }
}