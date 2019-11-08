package com.btsbetting.constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeagueConstants {

    public static final int ARGENTINA_1 = 780;
    public static final int AUSTRALIA_1 = 979;
    public static final int AUSTRIA_1 = 568;
    public static final int BELGIUM_1 = 656;
    public static final int BRAZIL_1 = 357;
    public static final int CZECH_REP = 579;
    public static final int DENMARK_1 = 515;
    public static final int EUROPA_LEAGUE = 514;
    public static final int CHAMPIONS_LEAGUE = 530;
    public static final int ENGLISH_PREMIER = 524;
    public static final int ENGLAND_2 = 565;
    public static final int ENGLAND_3 = 581;
    public static final int ENGLAND_4 = 582;
    public static final int FINLAND_1 = 361;
    public static final int FRENCH_1 = 525;
    public static final int FRENCH_2 = 526;
    public static final int GERMAN_1 = 754;
    public static final int GERMAN_2 = 755;
    public static final int GREECE_1 = 787;
    public static final int HUNGARY_1 = 655;
    public static final int ITALY_1 = 891;
    public static final int ITALY_2 = 902;
    public static final int MEXICO_1 = 584;
    public static final int NETHERLANDS_1 = 566;
    public static final int NETHERLANDS_2 = 571;
    public static final int NORWAY_1 = 287;
    public static final int PORTUGAL_1 = 766;
    public static final int POLAND_1 = 517;
    public static final int ROMANIA_1 = 589;
    public static final int RUSSIA_1 = 511;
    public static final int SCOTLAND_1 = 574;
    public static final int SLOVAKIA_1 = 533;
    public static final int SPAIN_1 = 775;
    public static final int SPAIN_2 = 776;
    public static final int SWEDEN_1 = 291;
    public static final int SWISS_1 = 576;
    public static final int TURKEY_1 = 782;
    public static final int CROATIA_1 = 535;
    public static final int UKRAINE_1 = 534;
    public static final int USA_1 = 294;

    private static List<Integer> leagueIds = Arrays.asList(
            USA_1, UKRAINE_1, SWISS_1, SWEDEN_1, SPAIN_2, SLOVAKIA_1, SCOTLAND_1, POLAND_1, EUROPA_LEAGUE, ENGLISH_PREMIER,
            NORWAY_1, NETHERLANDS_2, MEXICO_1, HUNGARY_1, GERMAN_2, FRENCH_2, FINLAND_1, ENGLAND_4, ENGLAND_3, ENGLAND_2,
            CHAMPIONS_LEAGUE, CZECH_REP, BRAZIL_1, FRENCH_1, GERMAN_1, ITALY_1, NETHERLANDS_1, PORTUGAL_1, ROMANIA_1,
            BELGIUM_1, AUSTRIA_1, ARGENTINA_1, RUSSIA_1, SPAIN_1, TURKEY_1, DENMARK_1,
            CROATIA_1, GREECE_1, AUSTRALIA_1, ITALY_2);

    public static  List<Integer> getLeagueIds() {
        return leagueIds;
    }
}
