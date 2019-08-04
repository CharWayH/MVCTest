import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        /**
        for(int i = 0;i<nums.length;i++){
                for(int j = i + 1;j < nums.length;j++){
                    if(nums[j] == target-nums[i]){
                        int[] a = new int[]{i,j};
                        return a;
                    }
                }

        }*/
        /**
        Map<Integer,Integer> map = new HashMap<>();
        for(int i = 0;i < nums.length;i++){
            map.put(i,nums[i]);
        }
        for(int i = 0;i < nums.length;i++){
            int complement = target - nums[i];
            if(map.containsKey(complement) && map.get(complement) != i){
                return new int[]{i,map.get(complement)};
            }
        }*/


        throw new IllegalArgumentException("No two sum solution");
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {0,4,3,0};
        int target = 0;
        int[] result = solution.twoSum(nums, target);
        System.out.println(result[0]+","+result[1]);
    }
}