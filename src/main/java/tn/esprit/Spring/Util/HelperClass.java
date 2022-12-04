package tn.esprit.Spring.Util;

import javafx.scene.input.DataFormat;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;

public class HelperClass {

    public static long computeDifferenceBetweenDateInDays(Date dateDebut, Date dateFin) {
        return ChronoUnit.DAYS.between((Temporal) dateDebut, (Temporal) dateFin);
    }
}
