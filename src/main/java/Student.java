import java.util.ArrayList;

public class Student {
	private long id=0; // идентификационный номер
    private String fio=""; // фамилия и инициалы
    Group group; // ссылка на группу (объект Group)
    ArrayList<Integer> marks = new ArrayList(); // контейнер оценок (ArrayList).
	
	Student(String fio, long id){
	 this.id = id;
	 this.fio = fio;
	}

    public long getId() { return id; }

    public String getFio() { return fio; }

    void addToGroup(Group group){
		this.group=group;
	}
	
	void addMark(int a){
	marks.add(a);
	}
	
	float calcAverageMark(){
		if (marks.size()==0) {return 0;}
		float sum=0;
		for (Integer mark : marks){
			sum+=mark;
		}
		return sum/marks.size();
	}
}

