package beans;

public enum Category {
    Electricity,Food,Restaurant,Vacation,SportProducts,Hotels;


    public static Category fromInt(int intValue) {
        return switch (intValue) {
            case 0 -> Electricity;
            case 1 -> Food;
            case 2 -> Restaurant;
            case 3 -> Vacation;
            case 4 -> SportProducts;
            case 5 -> Hotels;
            default -> throw new IllegalArgumentException("Invalid integer value: " + intValue);
        };
    }
    }
//int intValue = 1;
//MyEnum myEnum = MyEnum.fromInt(intValue);
//System.out.println(myEnum); // This will print VALUE2//
