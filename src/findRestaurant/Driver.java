package findRestaurant;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	public ArrayList<Restaurant> csvReader()
	{
		ArrayList<Restaurant> rest_list = new ArrayList<Restaurant>();
		String fileName = "sample_data.csv";
		String data;
		int temp=0;
		File file = new File(fileName);
		try {
			Scanner inputStream = new Scanner(file);
			while(inputStream.hasNext())
			{
				 data = inputStream.next();
				 String[] values = data.split(",");
				 if(Integer.parseInt(values[0]) != temp)
				 {
					    rest_list.add(new Restaurant());
	                    temp = Integer.parseInt(values[0]);
				 }
				 
				 Restaurant restObject = rest_list.get(temp-1);
				 restObject.rest_id = temp;
				 restObject.price.add(Float.parseFloat(values[1].trim()));
				 restObject.label.add(values[2].trim());
				 int i = 3;
				 if(values.length > 3)
				 {
					 restObject.price.add(Float.parseFloat(values[1].trim()));
					 restObject.label.add(values[i].trim());
					 i++;
				 }
				 rest_list.set(temp-1, restObject);
			}
			
			inputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return rest_list;
	}
	
	public static void main(String[] args) {
		Driver driver = new Driver();
		ArrayList<Restaurant>resList= driver.csvReader();
		int restIndex=0, restID=0,argList;
		double max_price = 99999.0;
		while(restIndex < resList.size())
		{
			Restaurant restObject2 = resList.get(restIndex);
			float tempResult= 0;
			argList = 0;
			for(int i=0;i<restObject2.label.size();i++)
			{
				for(int argIndex = 0;argIndex < args.length;argIndex++)
                {
                    if(args[argIndex].equalsIgnoreCase(restObject2.label.get(i)))
                    {
                        tempResult += restObject2.price.get(i);
                        argList++;
                    }
                }
			}
			
			 if(tempResult>0 && tempResult < max_price && argList == args.length)
	            {
	                max_price = tempResult;
	                restID = restObject2.rest_id;
	            }
			 restIndex++;
		}
		
		if(max_price < 99999) 
		{
            System.out.println(restID + " " + max_price);
        }
		else
		{
            System.out.println(false);
        }
		
	}

}
