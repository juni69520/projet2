package fr.epsi.epsig2;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Class IOProgram
 * 
 * @author Nidal DJEMAM
 * Vesion 1.0.0
 * created:21/07/2014
 * last update:21/07/2014
 */
public class IOProgram {
	static public Object load(Context myContext,String fileName){
		try	{
			FileInputStream fis = myContext.openFileInput(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			try{
				return ois.readObject();
			}
			finally{
				try{
					ois.close();
				}
				finally{
					fis.close();
				}
			}
		}
		catch (FileNotFoundException e)	{
			e.printStackTrace();
			return null;
		}
		catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	static public void save(Context myContext,Object obj,String fileName){
		try{
			FileOutputStream fos = myContext.openFileOutput(fileName, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			try{
				oos.writeObject(obj);
				oos.flush();
			}
			finally{
				try{
					oos.close();
				}
				finally{
					fos.close();
				}
			}
		}
		catch (FileNotFoundException e)	{
			e.printStackTrace();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	static public Object loadWithPath(Context myContext,String fileName){
		try	{
			File file = new File(fileName);
			if(!file.exists())
				return null;
			FileInputStream fis = new FileInputStream(file);//myContext.openFileInput(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			try{
				return ois.readObject();
			}
			finally{
				try{
					ois.close();
				}
				finally{
					fis.close();
				}
			}
		}
		catch (FileNotFoundException e)	{
			e.printStackTrace();
			return null;
		}
		catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	static public void saveWithPath(Context myContext,Object obj,String fileName){
		try{
			File file = new File(fileName);
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			try{
				oos.writeObject(obj);
				oos.flush();
			}
			finally{
				try{
					oos.close();
				}
				finally{
					fos.close();
				}
			}
		}
		catch (FileNotFoundException e)	{
			e.printStackTrace();
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}