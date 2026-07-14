package leetcode;



public class BestTimeToBuyAndSellStock {

    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

    /**
     * You are given an array prices where prices[i] is the price of a given stock on the ith day.
     * <p>
     * You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
     * <p>
     * Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.
     */

    public static int maxProfit1(int[] prices) {
        if (prices == null) {
            throw new IllegalArgumentException("prices must not be null");
        }
        long minimumPrice = Long.MAX_VALUE;
        long maximumProfit = 0L;
        for (int price : prices) {
            if (price < 0) {
                throw new IllegalArgumentException("prices must not be negative");
            }
            minimumPrice = Math.min(minimumPrice, price);
            maximumProfit = Math.max(maximumProfit, price - minimumPrice);
        }
        return Math.toIntExact(maximumProfit);
    }


}
