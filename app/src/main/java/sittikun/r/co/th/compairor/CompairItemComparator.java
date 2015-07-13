package sittikun.r.co.th.compairor;

import java.util.Comparator;

/**
 * Created by sittikun on 30/6/2558.
 */
public class CompairItemComparator implements Comparator<CompairItem> {
    @Override
    public int compare(CompairItem lhs, CompairItem rhs) {
        if (lhs.getPricePerUnit() < rhs.getPricePerUnit()) return -1;
        if (lhs.getPricePerUnit() > rhs.getPricePerUnit()) return 1;
        return 0;
    }
}
