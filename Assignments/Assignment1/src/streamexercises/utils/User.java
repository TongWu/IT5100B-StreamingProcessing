package streamexercises.utils;

/**
 * A user in our simple banking system. Objects of this class are immutable.
 */
public class User {
    private final String id;
    private final String name;
    private final long accountBalance;

    private User(String id) {
        this(id, null, 0);
    }

    private User(String id, String name, long accountBalance) {
        this.id = id;
        this.name = name;
        this.accountBalance = accountBalance;
    }

    /**
     * Creates a user with no name and an account balance of 0.
     * @param id The ID of the user.
     * @return The empty user.
     */
    public static User empty(String id) {
        return new User(id);
    }

    /**
     * Creates a new User where the name is replaced with a new name.
     * @param name The new name of the user.
     * @return The user with the new name.
     */
    public User ofName(String name) {
        return new User(id, name, accountBalance);
    }

    /**
     * Creates a new User where the account balance is replaced with a new account balance.
     * @param accountBalance The new account balance of the user.
     * @return The user with the new account balance.
     */
    public User ofAccountBalance(long accountBalance) {
        return new User(id, name, accountBalance);
    }

    /**
     * Get the ID of the user.
     * @return The ID of the user.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the name of the user.
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the account balance of the user.
     * @return The account balance of the user.
     */
    public long getAccountBalance() {
        return accountBalance;
    }

    /**
     * Associatively combines this user with another user with the same ID. The other user is assumed to have the most updated state.
     * When combining {@code this} user with {@code that} user, the resulting user has:
     * <ul>
     *     <li>if {@code that} has a name, the resulting user has {@code that}'s name; otherwise, the resulting user has {@code this}'s name</li>
     *     <li>the resulting user has an account balance of {@code this.accountBalance + that.accountBalance}</li>
     * </ul>
     * @param that The user to combine with.
     * @return The resulting user after combining.
     */
    public User combineWith(User that) {
        if (!this.id.equals(that.id))
            throw new IllegalArgumentException(String.format("cannot combine %s with %s as they have different IDs", this, that));
        return (that.name != null ? this.ofName(that.name) : this)
                .ofAccountBalance(this.accountBalance + that.accountBalance);
    }

    @Override
    public String toString() {
        return String.format("{id: %s, name: %s, accountBalance: %d}", id, name, accountBalance);
    }
    @Override
    public boolean equals(Object o) {
        User u;
        return (o instanceof User)
                && (u = (User) o).id.equals(id)
                && u.name.equals(name)
                && u.accountBalance == accountBalance;
    }
    @Override
    public int hashCode() {
        return (id + name + accountBalance).hashCode();
    }
}
