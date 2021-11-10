package de.devcyntrix.simple.module.reward;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.IOException;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class UserDataFile {

    private final File file;
    private PlayerRewards playerRewards;

    public void reload() {
        try {
            this.playerRewards = RewardModule.MAPPER.readValue(file, PlayerRewards.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            RewardModule.MAPPER.writeValue(file, playerRewards);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
