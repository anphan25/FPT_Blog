package swp.userlist;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.function.Predicate;

/*** DON'T PUT ME IN HORNY JAIL FOR THIS CODING SHIT ***/
public class Userlist implements Serializable
{
    private final ArrayList<UserlistDTO> fulllist;

    public Userlist(ArrayList<UserlistDTO> fulllist) //nạp lần đầu
    {
        this.fulllist = fulllist;
    }
    
    public ArrayList<UserlistDTO> getFullList() { return this.fulllist; }
    
    public ArrayList<UserlistDTO> filteredList(String gender, String status, String role, String major)
    {
        //tạm if else sau này có thời gian chuyển thành switch cave(động phò)
        ArrayList<UserlistDTO> result = new ArrayList<>(fulllist);
        String all = "all";
        //all of this relate to tree diagram
        boolean isMentor = role.equals("Mentor");      //this will determine whether the number 6 will split or not
        //relative to number 1 3 5 and 8
        boolean isFullGender = gender.equals(all); //1 false mean 2
        boolean isFullStatus = status.equals(all); //3 false mean 4
        boolean isFullRole = role.equals(all);     //5 false mean 6
        boolean isFullMajor = major.equals(all);   //7 false mean 8
        //CHECK all block that's exist 2 4 6 8 for the extra bug
        if(isFullGender && isFullStatus && isFullRole) // 1 3 5
        {
            return result;
        }
        else if(isFullGender && isFullStatus && !isFullRole) // 1 3 6
        {
            result.removeIf(otherrole -> !otherrole.getRole().equals(role));
            if(isMentor) // i love coding
            {
                if(isFullMajor) // 1 3 6 7
                {
                    return result;
                }
                else // 1 3 6 8
                {
                    result.removeIf(othermajor -> !othermajor.getMajor().equals(major));
                    return result;
                }
            }
            return result;
        }
        else if(isFullGender && !isFullStatus && isFullRole) //1 4 5
        {
            result.removeIf(otherstatus -> !otherstatus.getStatusaccount().equals(status));
            return result;
        }
        else if(isFullGender && !isFullStatus && !isFullRole) //1 4 6  PLEASE RUN THIS FIRST BEFORE 1 4 7
        {
            result.removeIf(otherrole -> !otherrole.getRole().equals(role));
            result.removeIf(otherstatus -> !otherstatus.getStatusaccount().equals(status));
            if(isMentor) // i love coding
            {
                if(isFullMajor) // 1 3 6 7
                {
                    return result;
                }
                else // 1 3 6 8
                {
                    result.removeIf(othermajor -> !othermajor.getMajor().equals(major));
                    return result;
                }
            }
            return result;
        }
        else if(!isFullGender && isFullStatus && isFullRole) // 2 3 5
        {
            result.removeIf(othergender -> !othergender.getGender().equals(gender));
            return result;
        }
        else if(!isFullGender && isFullStatus && !isFullRole) // 2 3 6
        {
            result.removeIf(othergender -> !othergender.getGender().equals(gender)); //2
            result.removeIf(otherrole -> !otherrole.getRole().equals(role));//6
            if(isMentor) // i love coding
            {
                if(isFullMajor) // 2 3 6 7
                {
                    return result;
                }
                else // 2 3 6 8
                {
                    result.removeIf(othermajor -> !othermajor.getMajor().equals(major));
                    return result;
                }
            }
            return result;
        }
        else if(!isFullGender && !isFullStatus && isFullRole) // 2 4 5
        {
            result.removeIf(othergender -> !othergender.getGender().equals(gender));
            result.removeIf(otherstatus -> !otherstatus.getStatusaccount().equals(status));
            return result;
        }
        else if(!isFullGender && !isFullStatus && !isFullRole) // 2 4 6
        {
            result.removeIf(othergender -> !othergender.getGender().equals(gender));
            result.removeIf(otherstatus -> !otherstatus.getStatusaccount().equals(status));
            result.removeIf(otherrole -> !otherrole.getRole().equals(role));
            if(isMentor) // i love coding
            {
                if(isFullMajor) // 2 4 6 7
                {
                    return result;
                }
                else // 2 4 6 8
                {
                    result.removeIf(othermajor -> !othermajor.getMajor().equals(major));
                    return result;
                }
            }
            return result;
        }
        return null;
    }
    
    public ArrayList<UserlistDTO> filteredList(String gender, String status, String role, String major, ArrayList<UserlistDTO> splist)
    {
        ArrayList<UserlistDTO> result = new ArrayList<>(splist);
        String all = "all";
        //all of this relate to tree diagram
        boolean isMentor = role.equals("Mentor");      //this will determine whether the number 6 will split or not
        //relative to number 1 3 5 and 8
        boolean isFullGender = gender.equals(all); //1 false mean 2
        boolean isFullStatus = status.equals(all); //3 false mean 4
        boolean isFullRole = role.equals(all);     //5 false mean 6
        boolean isFullMajor = major.equals(all);   //7 false mean 8
        //CHECK all block that's exist 2 4 6 8 for the extra bug
        if(isFullGender && isFullStatus && isFullRole) // 1 3 5
        {
            return result;
        }
        else if(isFullGender && isFullStatus && !isFullRole) // 1 3 6
        {
            result.removeIf(otherrole -> !otherrole.getRole().equals(role));
            if(isMentor) // i love coding
            {
                if(isFullMajor) // 1 3 6 7
                {
                    return result;
                }
                else // 1 3 6 8
                {
                    result.removeIf(othermajor -> !othermajor.getMajor().equals(major));
                    return result;
                }
            }
            return result;
        }
        else if(isFullGender && !isFullStatus && isFullRole) //1 4 5
        {
            result.removeIf(otherstatus -> !otherstatus.getStatusaccount().equals(status));
            return result;
        }
        else if(isFullGender && !isFullStatus && !isFullRole) //1 4 6  PLEASE RUN THIS FIRST BEFORE 1 4 7
        {
            result.removeIf(otherrole -> !otherrole.getRole().equals(role));
            result.removeIf(otherstatus -> !otherstatus.getStatusaccount().equals(status));
            if(isMentor) // i love coding
            {
                if(isFullMajor) // 1 3 6 7
                {
                    return result;
                }
                else // 1 3 6 8
                {
                    result.removeIf(othermajor -> !othermajor.getMajor().equals(major));
                    return result;
                }
            }
            return result;
        }
        else if(!isFullGender && isFullStatus && isFullRole) // 2 3 5
        {
            result.removeIf(othergender -> !othergender.getGender().equals(gender));
            return result;
        }
        else if(!isFullGender && isFullStatus && !isFullRole) // 2 3 6
        {
            result.removeIf(othergender -> !othergender.getGender().equals(gender)); //2
            result.removeIf(otherrole -> !otherrole.getRole().equals(role));//6
            if(isMentor) // i love coding
            {
                if(isFullMajor) // 2 3 6 7
                {
                    return result;
                }
                else // 2 3 6 8
                {
                    result.removeIf(othermajor -> !othermajor.getMajor().equals(major));
                    return result;
                }
            }
            return result;
        }
        else if(!isFullGender && !isFullStatus && isFullRole) // 2 4 5
        {
            result.removeIf(othergender -> !othergender.getGender().equals(gender));
            result.removeIf(otherstatus -> !otherstatus.getStatusaccount().equals(status));
            return result;
        }
        else if(!isFullGender && !isFullStatus && !isFullRole) // 2 4 6
        {
            result.removeIf(othergender -> !othergender.getGender().equals(gender));
            result.removeIf(otherstatus -> !otherstatus.getStatusaccount().equals(status));
            result.removeIf(otherrole -> !otherrole.getRole().equals(role));
            if(isMentor) // i love coding
            {
                if(isFullMajor) // 2 4 6 7
                {
                    return result;
                }
                else // 2 4 6 8
                {
                    result.removeIf(othermajor -> !othermajor.getMajor().equals(major));
                    return result;
                }
            }
            return result;
        }
        return null;
    }
    
    public ArrayList<UserlistDTO> searchAll(String searchtext)
    {//dont have time for revewing this one.
        //this gonna be cồng kềnh but who care
        ArrayList<UserlistDTO> name = new ArrayList<>(fulllist);
        ArrayList<UserlistDTO> email = new ArrayList<>(fulllist);
        name.removeIf(othername -> !othername.getName().contains(searchtext));
        ArrayList<UserlistDTO> result = new ArrayList<>(name);
        email.removeIf(otheremail -> !otheremail.getEmail().contains(searchtext));
        result.addAll(email);
        return result;
    }
    
    public ArrayList<UserlistDTO> searchSpecificEmail(String email, String domain)
    {
        ArrayList<UserlistDTO> result = new ArrayList<>(fulllist);
        if(domain.contains("fpt"))
            result.removeIf(nomentor -> nomentor.getEmail().contains("@fu.edu.vn"));
        else if(domain.contains("fu"))
            result.removeIf(other -> other.getEmail().contains("@fpt.edu.vn"));
        result.removeIf(otheremail -> !otheremail.getEmail().contains(email));
        return result;
    }
    
    public ArrayList<UserlistDTO> searchSpecificEmail(String email)
    {
        ArrayList<UserlistDTO> result = new ArrayList<>(fulllist);
        result.removeIf(otheremail -> !otheremail.getEmail().contains(email));
        return result;
    }
/*    remarkable code in memory of old code
if(gender.equals("all") && !status.equals("all")) //filt with status
{
    if(checksearch)
    {
        result.removeIf(thestatus -> !thestatus.getStatusaccount().equals(status));
    }
    else
    {
        result.removeIf(thestatus -> !thestatus.getStatusaccount().equals(status));
        //this is anonymous class practice from me dont mind it
        Predicate<UserlistDTO> condition = new Predicate<UserlistDTO>()
        {
            public boolean test(UserlistDTO user)
            {
                return !user.getEmail().contains(search) && !user.getName().contains(search);
            }
        };
        result.removeIf(condition);
    }
}
else if(status.equals("all") && !gender.equals("all")) //filt with gender
{
    if(checksearch)
    {
        result.removeIf(thegender -> !thegender.getGender().equals(gender));
    }
    else
    {
        result.removeIf(thegender -> !thegender.getGender().equals(gender));
        Predicate<UserlistDTO> condition = user -> !user.getEmail().contains(search) && !user.getName().contains(search);
        result.removeIf(condition);
        //tách ra cho dễ kiểm soát
    }
}
else //cả 2 đều được chọn
{
    if(checksearch)//search ko có gì cả
    {
        if(status.equals("all") && gender.equals("all"))
        {
            //the list is not change if it execute this block only in the whole if else storm
        }
        else
        {
            Predicate<UserlistDTO> condition = user -> !user.getGender().equals(gender) || !user.getStatusaccount().equals(status);
            result.removeIf(condition);
        }
    }
    else //có search
    {
        if(status.equals("all") && gender.equals("all"))
        {
            Predicate<UserlistDTO> conditionsearch = user -> !user.getEmail().contains(search) && !user.getName().contains(search);
            result.removeIf(conditionsearch);
        }
        else
        {
            Predicate<UserlistDTO> conditionchoice = user -> !user.getGender().equals(gender) || !user.getStatusaccount().equals(status);
            Predicate<UserlistDTO> conditionsearch = user -> !user.getEmail().contains(search) && !user.getName().contains(search);
            result.removeIf(conditionchoice);
            result.removeIf(conditionsearch);
        }
    }
}*/
}
