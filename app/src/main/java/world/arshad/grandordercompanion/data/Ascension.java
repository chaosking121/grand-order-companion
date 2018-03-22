package world.arshad.grandordercompanion.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.Relation;

import java.util.List;

import static android.arch.persistence.room.ForeignKey.CASCADE;

/**
 * This class represents a given ascension.
 * Created by arshad on 18/03/2018.
 */

@Entity(tableName = "ascension",
        primaryKeys = {"ascension_number", "servant_id"})
public class Ascension {
    public static final int DONTCARE = 0;
    public static final int TRACKED = 1;
    public static final int COMPLETED = 2;

    @ColumnInfo(name = "ascension_number")
    private int ascensionNumber;

    @ColumnInfo(name = "servant_id")
    private int servantId;

    @ColumnInfo(name = "status")
    private int status;

    @Ignore
    private List<AscensionEntry> ascensionEntries;

    public Ascension(int ascensionNumber, int servantId, int status) {
        this.ascensionNumber = ascensionNumber;
        this.servantId = servantId;
        this.status = status;
    }

    public int getAscensionNumber() {
        return ascensionNumber;
    }

    public void setAscensionNumber(int ascensionNumber) {
        this.ascensionNumber = ascensionNumber;
    }

    public int getServantId() {
        return servantId;
    }

    public void setServantId(int servantId) {
        this.servantId = servantId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<AscensionEntry> getAscensionEntries() {
        return ascensionEntries;
    }

    public void setAscensionEntries(List<AscensionEntry> ascensionEntries) {
        this.ascensionEntries = ascensionEntries;
    }

    @Override
    public String toString() {
        return String.format("Ascension #%d", ascensionNumber);
    }
}
