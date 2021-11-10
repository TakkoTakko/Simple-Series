package de.devcyntrix.simple.module.reward;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reward {

    private String id;
    private String displayName;
    private long duration;
    private int slot;

}
