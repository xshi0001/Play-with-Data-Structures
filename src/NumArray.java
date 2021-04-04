class NumArray {
    // 存储求和值,sums.length-1 =data.length
    private int[] sums;
    // 存储原始数据
    private int[] data;

    public NumArray(int[] nums) {
        data = new int[nums.length];
        System.arraycopy(nums, 0, data, 0, nums.length);
        sums = new int[nums.length + 1];
        // 初始值
        sums[0] = 0;
        for (int i = 1; i < nums.length+1; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
    }

    public void update(int index, int val) {
        data[index] = val;
        for (int i = index + 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + data[i-1];
        }
    }
    public int sumRange(int left, int right) {
        return sums[right +1] - sums[left];
    }

    public static void main(String[] args) {
        NumArray numArray = new NumArray(new int[]{1, 3, 5});
        System.out.println("numArray.sumRange(0,2) = " + numArray.sumRange(0, 2));
        numArray.update(1, 2);
        System.out.println("numArray.sumRange(0,2) = " + numArray.sumRange(0, 2));


    }
}
