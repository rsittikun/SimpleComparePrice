package sittikun.r.co.th.compairor;

import java.io.Serializable;

/**
 * Created by sittikun on 29/6/2558.
 */
public class CompairItem implements Serializable{

    public CompairItem(String price, String unit) {
        this.price = price;
        this.unit = unit;

    }

    public String price;
    public String unit;

    public double getPricePerUnit(){
        return Double.parseDouble(price)/Integer.parseInt(unit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompairItem that = (CompairItem) o;

        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return !(unit != null ? !unit.equals(that.unit) : that.unit != null);

    }

    @Override
    public int hashCode() {
        int result = price != null ? price.hashCode() : 0;
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        return result;
    }
}
