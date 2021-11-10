package de.devcyntrix.simple.module.reward.util;

import java.text.DateFormatSymbols;
import java.text.spi.DateFormatSymbolsProvider;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class Time {

    public static String formatRemaining(long remainingMillis, Locale locale) {

        StringBuilder builder = new StringBuilder();
        Duration duration = Duration.ofMillis(remainingMillis);

        long days = duration.toDaysPart();
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        if (days > 0) {
            builder.append(days).append(" Tag").append(days != 1 ? "e" : "");
        }

        if (hours > 0) {
            if (!builder.isEmpty())
                builder.append(", ");
            builder.append(hours).append(" Stunde").append(hours != 1 ? "n" : "");
        }

        if (minutes > 0) {
            if (!builder.isEmpty())
                builder.append(", ");
            builder.append(minutes).append(" Minute").append(minutes != 1 ? "n" : "");
        }

        if (seconds > 0) {
            if (!builder.isEmpty())
                builder.append(", ");
            builder.append(seconds).append(" Sekunde").append(seconds != 1 ? "n" : "");
        }

        return builder.toString();
    }

}
