package com.winestore.domain.entity.product;

import com.winestore.domain.entity.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
@NoArgsConstructor
public class UserWinePK implements Serializable {

    @Serial
    private static final long serialVersionUID = 2392960520065163927L;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wine_id")
    private Wine wine;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserWinePK that = (UserWinePK) o;
        return user.equals(that.user) && wine.equals(that.wine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, wine);
    }
}
