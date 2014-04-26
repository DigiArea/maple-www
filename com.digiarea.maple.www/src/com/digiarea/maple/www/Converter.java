package com.digiarea.maple.www;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Converter {

	private static final String WORKSHEET = "<Worksheet>";

	private static final String MW = ".mw";

	public enum Status {
		SRC, DST, URL, KIND, NONE
	}

	public enum Kind {
		PLOT, EQUATION, DRAWING_CANVAS, DRAWING_ROOT, ANY
	}

	private final static Logger LOGGER = Logger.getLogger(Converter.class
			.getName());

	private static final boolean DEBUG = true;

	public static void main(String[] args) {
		String src0 = null;
		String dst0 = null;
		String url0 = null;
		Kind kind0 = Kind.ANY;
		Status status = Status.NONE;
		for (int i = 0; i < args.length; i++) {
			if ("-src".equals(args[i])) {
				status = Status.SRC;
			} else if (status == Status.SRC) {
				src0 = args[i];
				status = Status.NONE;
			} else if ("-dst".equals(args[i])) {
				status = Status.DST;
			} else if (status == Status.DST) {
				dst0 = args[i];
				status = Status.NONE;
			} else if ("-url".equals(args[i])) {
				status = Status.URL;
			} else if (status == Status.URL) {
				url0 = args[i];
				status = Status.NONE;
			} else if ("-kind".equals(args[i])) {
				status = Status.KIND;
			} else if (status == Status.KIND) {
				try {
					kind0 = Kind.valueOf(args[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				status = Status.NONE;
			}
		}
		final String src = src0;
		final String dst = dst0;
		final String url = url0;
		final Kind kind = kind0;
		if (src == null || dst == null || url == null) {
			System.out.println("Usage:");
			System.out
					.println("com.digiarea.maple.www.Converter\n"
							+ " -src PATH_TO_FOLDER_WITH_MW_FILES\n"
							+ " -dst PATH_TO_FOLDER_FOR_MW_OUTPUT\n"
							+ " -url URL to MapleWWW JS script\n"
							+ " -kind [PLOT, EQUATION, DRAWING_CANVAS, DRAWING_ROOT, ANY]");
			System.exit(1);
		}
		final String subs = makeSubs(url);
		try {
			Handler handler = new FileHandler(kind.name() + "-CONVERTER.xml",
					1024 * 1024 * 1024, 100);
			LOGGER.addHandler(handler);
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		//
		Path p = Paths.get(src);
		FileVisitor<Path> fv = new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file,
					BasicFileAttributes attrs) throws IOException {
				// System.out.println(file);
				if (file.toFile().toString().endsWith(MW)) {
					try {
						byte[] encoded = Files.readAllBytes(file);
						String content = new String(encoded);
						if (isPrintable(kind, content)) {
							content = content.replace(WORKSHEET, subs);
							Path out = makePath(src, dst, file);
							makeDirs(out);
							try (FileOutputStream fos = new FileOutputStream(
									out.toFile())) {
								fos.write(content.getBytes());
							} catch (IOException e) {
								if (DEBUG) {
									e.printStackTrace();
								}
								LOGGER.log(Level.WARNING, e.toString());
							}
						}
					} catch (Exception e) {
						if (DEBUG) {
							e.printStackTrace();
						}
						LOGGER.log(Level.WARNING, file.toString());
						LOGGER.log(Level.WARNING, e.toString());
					}
				}
				return FileVisitResult.CONTINUE;
			}

			private boolean isPrintable(final Kind kind, String content) {
				return kind == Kind.ANY
						|| (kind == Kind.PLOT && content.contains("<Plot"))
						|| (kind == Kind.EQUATION && content
								.contains("<Equation"))
						|| (kind == Kind.DRAWING_CANVAS && content
								.contains("<Drawing-Canvas"))
						|| (kind == Kind.DRAWING_ROOT && content
								.contains("<Drawing-Root"));
			}

		};
		try {
			Files.walkFileTree(p, fv);
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, e.toString());
		} finally {
			// close dst
		}
	}

	private static void makeDirs(Path p) throws IOException {
		Path dir = p.getParent();
		if (!dir.toFile().exists()) {
			Files.createDirectories(dir, new FileAttribute<?>[] {});
		}
		if (p.toFile().exists()) {
			Files.delete(p);
		}
	}

	private static Path makePath(String src, String dst, Path file) {
		Path relPath = Paths.get(src).relativize(file);
		String fileName = file.getFileName().toString();
		if (relPath.getParent() != null) {
			relPath = relPath.getParent().resolve(fileName);
		} else {
			relPath = Paths.get(fileName);
		}
		return Paths.get(dst).resolve(relPath);
	}

	private static String makeSubs(String url) {
		return "<Worksheet>\n"
				+ "<xhtml:script xmlns:xhtml=\"http://www.w3.org/1999/xhtml\" "
				+ "src=\"" + url + "\" " + "type=\"application/javascript\"/>";
	}
}
