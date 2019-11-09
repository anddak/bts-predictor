package com.btsbetting.constants;

import java.util.Arrays;
import java.util.List;

public class LeagueConstants {

    private static final int ARGENTINA_1 = 780;
    private static final int AUSTRALIA_1 = 979;
    private static final int AUSTRIA_1 = 568;
    private static final int BELGIUM_1 = 656;
    private static final int BRAZIL_1 = 357;
    private static final int CZECH_REP = 579;
    private static final int DENMARK_1 = 515;
    private static final int EUROPA_LEAGUE = 514;
    private static final int CHAMPIONS_LEAGUE = 530;
    private static final int ENGLISH_PREMIER = 524;
    private static final int ENGLAND_2 = 565;
    private static final int ENGLAND_3 = 581;
    private static final int ENGLAND_4 = 582;
    private static final int FINLAND_1 = 361;
    private static final int FRENCH_1 = 525;
    private static final int FRENCH_2 = 526;
    private static final int GERMAN_1 = 754;
    private static final int GERMAN_2 = 755;
    private static final int GREECE_1 = 787;
    private static final int HUNGARY_1 = 655;
    private static final int ITALY_1 = 891;
    private static final int ITALY_2 = 902;
    private static final int MEXICO_1 = 584;
    private static final int NETHERLANDS_1 = 566;
    private static final int NETHERLANDS_2 = 571;
    private static final int NORWAY_1 = 287;
    private static final int PORTUGAL_1 = 766;
    private static final int POLAND_1 = 517;
    private static final int ROMANIA_1 = 589;
    private static final int RUSSIA_1 = 511;
    private static final int SCOTLAND_1 = 574;
    private static final int SLOVAKIA_1 = 533;
    private static final int SPAIN_1 = 775;
    private static final int SPAIN_2 = 776;
    private static final int SWEDEN_1 = 291;
    private static final int SWISS_1 = 576;
    private static final int TURKEY_1 = 782;
    private static final int CROATIA_1 = 535;
    private static final int UKRAINE_1 = 534;
    private static final int USA_1 = 294;

    private LeagueConstants() {
        throw new IllegalStateException("Utility class");
    }

    private static List<Integer> leagueIds = Arrays.asList(
            USA_1, UKRAINE_1, SWISS_1, SWEDEN_1, SPAIN_2, SLOVAKIA_1, SCOTLAND_1, POLAND_1, EUROPA_LEAGUE, ENGLISH_PREMIER,
            NORWAY_1, NETHERLANDS_2, MEXICO_1, HUNGARY_1, GERMAN_2, FRENCH_2, FINLAND_1, ENGLAND_4, ENGLAND_3, ENGLAND_2,
            CHAMPIONS_LEAGUE, CZECH_REP, BRAZIL_1, FRENCH_1, GERMAN_1, ITALY_1, NETHERLANDS_1, PORTUGAL_1, ROMANIA_1,
            BELGIUM_1, AUSTRIA_1, ARGENTINA_1, RUSSIA_1, SPAIN_1, TURKEY_1, DENMARK_1,
            CROATIA_1, GREECE_1, AUSTRALIA_1, ITALY_2);

    public static List<Integer> getLeagueIds() {
        return leagueIds;
    }
}
