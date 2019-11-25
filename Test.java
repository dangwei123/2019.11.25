给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。
class Solution {
    public int maxSumDivThree(int[] nums) {
        int[] dp = new int[3];
        for(int i = 0 ; i < nums.length ; i ++){
            int mod = nums[i] % 3;
            int a = dp[(3 + 0 - mod) % 3];
            int b = dp[(3 + 1 - mod) % 3];
            int c = dp[(3 + 2 - mod) % 3];
            if (a!=0 || mod == 0) dp[0] = Math.max(dp[0], a + nums[i]);
            if (b!=0 || mod == 1) dp[1] = Math.max(dp[1], b + nums[i]);
            if (c!=0 || mod == 2) dp[2] = Math.max(dp[2], c + nums[i]);
        }
        return dp[0];
    }
}

编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

每行中的整数从左到右按升序排列。
每行的第一个整数大于前一行的最后一个整数。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/search-a-2d-matrix
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
方法一：
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m=matrix.length;
        if(m==0){
            return false;
        }
        int n=matrix[0].length;
        int i=0;
        int j=n-1;
        while(i<m&&j>=0){
            int mid=matrix[i][j];
            if(mid==target){
                return true;
            }else if(mid>target){
                j--;
            }else{
                i++;
            }
        }
        return false;
    }
}

方法二：
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m=matrix.length;
        if(m==0){
            return false;
        }
        int n=matrix[0].length;
        int l=0;
        int r=m*n-1;
        while(l<=r){
            int mid=(l+r)>>>1;
            int tmp=matrix[mid/n][mid%n];
            if(tmp==target){
                return true;
            }else if(tmp>target){
                r=mid-1;
            }else{
                l=mid+1;
            }
        }
        return false;
    }
}


给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数
字是单调递增。

（当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/monotone-increasing-digits
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
class Solution {
    public int monotoneIncreasingDigits(int N) {
       int n=N;
       String s="";
       while(n!=0){
           s+=n%10;
           n/=10;
       }
       char[] res=s.toCharArray();
       int l=0;
       int r=res.length-1;
       while(l<r){
           char tmp=res[l];
           res[l++]=res[r];
           res[r--]=tmp; 
       }
       int flag=res.length;
       for(int i=res.length-1;i>0;i--){
           if(res[i]<res[i-1]){
               res[i-1]--;
               flag=i;
           }
       }
       for(;flag<res.length;flag++){
           res[flag]='9';
       }
       int sum=0;
       for(int i=0;i<res.length;i++){
           sum=sum*10+(res[i]-'0');
       }
       return sum;
    }
}
