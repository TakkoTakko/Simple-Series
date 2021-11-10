package de.devcyntrix.simple.module.reward;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RewardConfiguration {

    private String prefix = "§3Reward §8︳§7 ";

    @JsonProperty("entity")
    private RewardEntity entity;

    private Reward[] rewards = new Reward[]{
            new Reward("example", "Example", 1000 * 5, 13)
    };

}
