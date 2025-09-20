import com.example.Social.Media.Application.entity.User;

public class UserFollowerDTO {
    private Long id;
    private String username;
    private String email;
    private Long followers;
    private Long following;

    // Constructor, getters, setters
    public UserFollowerDTO(User user) {
        this.id = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.followers = user.getFollowers();
        this.following = user.getFollowing();
    }
}
