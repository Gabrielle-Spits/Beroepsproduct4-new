package com.example.beroepsproduct4.database;

import android.provider.BaseColumns;

import java.time.LocalDateTime;

public class RollatorDataContract {

    private RollatorDataContract(){

    }
    public static class Zorgcentrums implements BaseColumns {
        public static final String TABLE_NAME = "zorgcentrums";
        public static final String Column_NAME_Afdeling= "afdeling";
        public static final String COLUMN_NAME_Zorgcentrum = "zorgcentrum";

    }

    public static class Ouderengegevens implements BaseColumns{
        public static final String TABLE_NAME = "ouderengegevens";
        public  static final String Column_Name_Bsn = "bsn";
        public static final String Column_Name_Oudernaam = "oudernaam";
        public static final String Column_NAME_Afdeling = "afdeling";
        public static final String Column_NAME_Zorgcentrum = "zorgcentrum";
    }

    public static class Rollatorhorenbij implements BaseColumns{
        public static final String TABLE_NAME = "rollatorhorenbij";
        public  static final String Column_Name_Bsn = "bsn";
        public static final String Column_Name_Rollator = "rollator";

    }

    public static class Rollatorgegevens  implements BaseColumns{
        public static final String TABLE_NAME = "rollatorgegevens";
        public static final String Column_Name_Rollator = "rollator";
        public static final String Column_Name_Date = "datum";
    }


}
