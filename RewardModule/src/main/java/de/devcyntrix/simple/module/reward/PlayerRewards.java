package de.devcyntrix.simple.module.reward;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class PlayerRewards {

    private Map<String, Long> gotRewards = new HashMap<>();

}
